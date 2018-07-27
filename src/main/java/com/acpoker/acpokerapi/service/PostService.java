package com.acpoker.acpokerapi.service;

import com.acpoker.acpokerapi.entity.Comment;
import com.acpoker.acpokerapi.entity.Post;
import com.acpoker.acpokerapi.model.*;
import com.acpoker.acpokerapi.repository.CommentRepository;
import com.acpoker.acpokerapi.repository.PostRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PostService {

    private static Logger LOGGER = Logger.getLogger(PostService.class);

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    public Page<Post> findAll(Pageable pageable) {
        LOGGER.info("LOOL");
        return postRepository.findAll(pageable);
    }

    public boolean addPost(Post post) {
        postRepository.save(post);
        return true;
    }

    public boolean editPost(Post post) {
        postRepository.save(post);
        return true;
    }

    public boolean deletePost(Integer id) {
        postRepository.delete(postRepository.findPostById(id));
        return true;
    }

    // public Page<Post>

    public PostDTO findById(Integer id) {
        Post post = postRepository.findPostById(id);
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setContent(post.getContent().split("\n"));
        postDTO.setDate(post.getDate());
        postDTO.setEditDate(post.getEditDate());
        postDTO.setTitle(post.getTitle());
        postDTO.setUid(post.getUid());
        postDTO.setUsername(post.getUsername());
        postDTO.setViews(post.getViews());
        postDTO.setPokerHouse(post.getPokerHouse());

        return postDTO;
    }

    public Game findHandByPostId(Integer id) {

        Post post = postRepository.findPostById(id);
        String content = post.getContent();
        Game game = new Game();
        List<Seat> seatList = new ArrayList<>();
        List<Action> preFlopActionsList = new ArrayList<>();
        List<Action> flopActionsList = new ArrayList<>();
        List<Action> turnActionsList = new ArrayList<>();
        List<Action> riverActionsList = new ArrayList<>();
        List<Action> showDownActionsList = new ArrayList<>();

        String userSmall = "";
        String userBig = "";
        String userButton = "";

        String[] contentArray = content.split("\n");

        for (int i = 0; i < contentArray.length; i++) {
            int rowLength = contentArray[i].split(" ").length;
            if (contentArray[i].toLowerCase().contains("seat") && i < 20) {
                if (contentArray[i].contains(":")) {
                    Seat seat = new Seat();

                    String[] userArray = contentArray[i].split(" ");
                    String userName = "";
                    int yy = 0;
                    int xx = 0;

                    for (int k = 0; k < userArray.length; k++) {
                        if (userArray[k].contains(":")) {
                            seat.setNumber(Integer.valueOf(userArray[k].replace(":", "")));
                            xx = k;
                        }
                        if (userArray[k].contains("(")) {
                            seat.setChips(new BigDecimal(userArray[k].replaceAll("\\(", "")));
                            yy = k;
                        }
                        if (k > xx && yy == 0) {
                            if (userName.equals("")) {
                                userName = userArray[k];
                            } else {
                                userName = userName + " " + userArray[k];
                            }
                        }
                    }
                    seat.setUser(userName);
                    seatList.add(seat);
                } else {
                    game.setButtonSeat(Integer.valueOf(contentArray[i].split(" ")[5].replace("#", "").trim()));
                }
            }

            if (contentArray[i].toLowerCase().contains("posts small blind") && i > 10) {
                userSmall = contentArray[i].split(":")[0].replace(':', ' ').trim();
                game.setSmallBlind(new BigDecimal(contentArray[i].split(" ")[rowLength - 1].trim()));
            }

            if (contentArray[i].toLowerCase().contains("posts big blind") && i > 10) {
                userBig = contentArray[i].split(":")[0].replace(':', ' ').trim();
                game.setBigBlind(new BigDecimal(contentArray[i].split(" ")[rowLength - 1].trim()));
            }

            if (contentArray[i].toLowerCase().contains("posts the ante") && i > 10) {
                game.setAnte(new BigDecimal(contentArray[i].split(" ")[rowLength - 1].trim()));
            }

            //PRE FLOP
            if (contentArray[i].toLowerCase().contains("*")) {
                if (contentArray[i].toLowerCase().contains("hole")) {
                    game = setActions(contentArray, i, preFlopActionsList, game, ActionPhase.PREFLOP);
                }
                if (contentArray[i].toLowerCase().contains("flop")) {
                    String firstCard = contentArray[i].split(" ")[3].replace('[', ' ').trim();
                    String secondCard = contentArray[i].split(" ")[4].trim();
                    String thirdCard = contentArray[i].split(" ")[5].replace(']', ' ').trim();
                    game.setFlop(firstCard + " " + secondCard + " " + thirdCard);

                    //ACTIONS ON FLOP
                    game = setActions(contentArray, i, flopActionsList, game, ActionPhase.FLOP);
                }
                if (contentArray[i].toLowerCase().contains("turn")) {
                    game.setTurn(contentArray[i].split(" ")[6].replace("[", "").replace("]", "").trim());

                    //ACTIONS ON TURN
                    game = setActions(contentArray, i, turnActionsList, game, ActionPhase.TURN);
                }
                if (contentArray[i].toLowerCase().contains("river")) {
                    game.setRiver(contentArray[i].split(" ")[7].replace("[", "").replace("]", "").trim());

                    //ACTIONS ON RIVER
                    game = setActions(contentArray, i, riverActionsList, game, ActionPhase.RIVER);
                }
                if (contentArray[i].toLowerCase().contains("show down")) {
                    game = setActions(contentArray, i, showDownActionsList, game, ActionPhase.SHOW_DOWN);
                }
            }
        }
        game.setPokerHouse(post.getPokerHouse());
        game.setSeats(seatList);

        for (int i = 0; i < contentArray.length; i++) {
            if (contentArray[i].toLowerCase().contains("shows")) {
                String user = contentArray[i].split(" ")[0].replace(':', ' ').trim();
                String cardOne = contentArray[i].split(" ")[2].replace('[', ' ').trim();
                String cardTwo = contentArray[i].split(" ")[3].replace(']', ' ').trim();

                game.getSeats().forEach(seat -> {
                    if (seat.getUser().equals(user)) {
                        seat.setCardOne(cardOne);
                        seat.setCardTwo(cardTwo);
                    }
                });
            }

            if (contentArray[i].toLowerCase().contains("hole cards")) {
                int myUserLength = contentArray[i + 1].split(" ").length;
                String cardOne = contentArray[i + 1].split(" ")[myUserLength - 2].replace('[', ' ').trim();
                String cardTwo = contentArray[i + 1].split(" ")[myUserLength - 1].replace(']', ' ').trim();
                String myUser = game.getMyNick();

                game.getSeats().forEach(seat -> {
                    if (seat.getUser().equals(myUser)) {
                        seat.setCardOne(cardOne);
                        seat.setCardTwo(cardTwo);
                    }
                });
            }
            if (contentArray[i].toLowerCase().contains("total pot")) {
                String amount = contentArray[i].split(" ")[2].trim();
                game.setFinalPot(new BigDecimal(amount));
            }

        }
        String uSm = userSmall;
        String uBm = userBig;
        int buttonNbr = game.getButtonSeat();

        game.getSeats().forEach(seat -> {
            if (seat.getUser().equals(uSm)) {
                seat.setSmall(true);
            }
            if (seat.getUser().equals(uBm)) {
                seat.setBig(true);
            }
            if (seat.getNumber() == buttonNbr) {
                seat.setButton(true);
            }
        });



        game.getSeats().sort(Comparator.comparingInt(Seat::getNumber));

        return game;
    }

    private Game setActions(String[] contentArray, int i, List<Action> actionsList, Game game, ActionPhase actionPhase) {
        int index;
        if (actionPhase == ActionPhase.PREFLOP) {
            game.setMyNick(contentArray[i + 1].split(" ")[2]);
            String[] myInfo = contentArray[i + 1].split(" ");
            String myHand = myInfo[myInfo.length - 2].replace("[", "") + " " + myInfo[myInfo.length - 1].replace("]", "");
            game.setMyHand(myHand);

            index = i + 2;
        } else {
            index = i + 1;
        }

        for (int x = index; x <= contentArray.length - 1; x++) {

            if (contentArray[x].toLowerCase().contains("*")) {
                break;
            }

            String[] actions = contentArray[x].split(" ");
            Action action;
            String userName = "";

            for (int y = 0; y < actions.length; y++) {
                if (y == 0) {
                    userName = actions[y];
                }

                if (!actions[y].toLowerCase().equals("folds") && !actions[y].toLowerCase().equals("checks") && !actions[y].toLowerCase().equals("calls") && !actions[y].toLowerCase().equals("bets")
                        && !actions[y].toLowerCase().equals("is") && !actions[y].toLowerCase().equals("folds") && !actions[y].toLowerCase().equals("collected") && !actions[y].toLowerCase().equals("raises") && y > 0) {
                    if (actions[y].equals("[")) {
                        break;
                    } else {
                        userName = userName + " " + actions[y];
                    }
                }
                action = new Action();
                if (actions[y].toLowerCase().equals("folds")) {
                    userName = userName.replaceAll(":", "");
                    action.setAmount(new BigDecimal(0));
                    action.setUserName(userName);
                    action.setActionType(ActionType.FOLD);
                    actionsList.add(action);
                } else if (actions[y].toLowerCase().equals("checks")) {
                    userName = userName.replaceAll(":", "");
                    action.setAmount(new BigDecimal(0));
                    action.setUserName(userName);
                    action.setActionType(ActionType.CHECK);
                    actionsList.add(action);
                } else if (actions[y].toLowerCase().equals("calls")) {
                    userName = userName.replaceAll(":", "");
                    action.setUserName(userName);
                    action.setActionType(ActionType.CALL);
                    String amount = actions[y + 1].replace("[", "").replace("]", "").replaceAll(",", "").trim();
                    action.setAmount(new BigDecimal(amount));
                    actionsList.add(action);
                } else if (actions[y].toLowerCase().equals("all-in")) {
                    userName = userName.replaceAll(":", "");
                    action.setUserName(userName);
                    action.setActionType(ActionType.ALLIN);
                    String amount = actions[2].trim();
                    action.setAmount(new BigDecimal(amount));
                    actionsList.add(action);
                } else if (actions[y].toLowerCase().equals("raises")) {
                    userName = userName.replaceAll(":", "");
                    action.setUserName(userName);
                    action.setActionType(ActionType.RAISE);
                    String amount = actions[y + 1].replace("[", "").replace("]", "").replaceAll(",", "").trim();
                    action.setAmount(new BigDecimal(amount));
                    actionsList.add(action);
                } else if (actions[y].toLowerCase().equals("bets")) {
                    userName = userName.replaceAll(":", "");
                    action.setUserName(userName);
                    action.setActionType(ActionType.BETS);
                    String amount = actions[y + 1].replace("[", "").replace("]", "").replaceAll(",", "").trim();
                    action.setAmount(new BigDecimal(amount));
                    actionsList.add(action);
                } else if (actions[y].toLowerCase().equals("collected")) {
                    userName = userName.replaceAll(":", "");
                    action.setUserName(userName);
                    action.setActionType(ActionType.COLLECT);
                    String amount = actions[y + 1].replace("[", "").replace("]", "").replaceAll(",", "").trim();
                    action.setAmount(new BigDecimal(amount));
                    actionsList.add(action);
                } else if (actions[y].toLowerCase().equals("shows")) {
                    action.setUserName(userName.split(":")[0]);
                    action.setActionType(ActionType.SHOWS);
                    actionsList.add(action);
                } else if (actions[y].toLowerCase().equals("mucks")) {
                    action.setUserName(userName.split(":")[0]);
                    action.setActionType(ActionType.MUCKS);
                    actionsList.add(action);
                }

            }

            if (actionPhase == ActionPhase.PREFLOP) {
                game.setPreFlopActions(actionsList);
            }
            if (actionPhase == ActionPhase.FLOP) {
                game.setFlopActions(actionsList);
            }
            if (actionPhase == ActionPhase.TURN) {
                if(!actionsList.isEmpty()) {
                    game.setTurnActions(actionsList);
                }
            }
            if (actionPhase == ActionPhase.RIVER) {
                if (!actionsList.isEmpty())
                    game.setRiverActions(actionsList);
            }
            if (actionPhase == ActionPhase.SHOW_DOWN) {
                if(!actionsList.isEmpty()) {
                    game.setShowDown(actionsList);
                }
            }

        }

        return game;
    }

    public boolean addComment(Comment comment) {
        commentRepository.save(comment);
        return true;
    }

    public List<Comment> findAllComments(Integer id) {
        Post post = postRepository.findPostById(id);
        return commentRepository.findCommentsByPost(post);
    }

    public boolean deleteComment(Integer id) {
        commentRepository.delete(commentRepository.findCommentById(id));
        return true;
    }
}
