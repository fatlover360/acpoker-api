package com.acpoker.acpokerapi.service;

import com.acpoker.acpokerapi.entity.Cards;
import com.acpoker.acpokerapi.entity.Comment;
import com.acpoker.acpokerapi.entity.Post;
import com.acpoker.acpokerapi.model.*;
import com.acpoker.acpokerapi.repository.CardsRepository;
import com.acpoker.acpokerapi.repository.CommentRepository;
import com.acpoker.acpokerapi.repository.PostRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    private static Logger LOGGER = Logger.getLogger(PostService.class);

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    public Page<Post> findAll(Pageable pageable) {
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

        String[] contentArray = content.split("\n");

        for (int i = 0; i < contentArray.length; i++) {
            if (contentArray[i].toLowerCase().contains("seat")) {
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
                        if (userArray[k].equals("(")) {
                            seat.setChips(new BigDecimal(userArray[k + 1].replaceAll(",", "")));
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
                    game.setButtonSeat(Integer.valueOf(contentArray[i].split(" ")[1].trim()));
                }
            }

            if (contentArray[i].toLowerCase().contains("blinds") && i > 10) {
                game.setBigBlind(new BigDecimal(contentArray[i].split("\\(")[1].split("-")[0].split("/")[0].replace(".", "")));
                game.setSmallBlind(new BigDecimal(contentArray[i].split("\\(")[1].split("-")[0].split("/")[1].replace(",", "").trim()));
                game.setAnte(new BigDecimal(contentArray[i].split("\\(")[1].split("-")[1].replace(")", "").trim()));
            }

            //PRE FLOP
            if (contentArray[i].toLowerCase().contains("*")) {
                if (contentArray[i].toLowerCase().contains("down")) {
                  game = setActions(contentArray, i, preFlopActionsList, game, ActionPhase.PREFLOP);
                }
                if (contentArray[i].toLowerCase().contains("flop")) {
                    String firstCard = contentArray[i].split(" ")[5].replace(',', ' ').trim();
                    String secondCard = contentArray[i].split(" ")[6].replace(',', ' ').trim();
                    String thirdCard = contentArray[i].split(" ")[7].replace(',', ' ').trim();
                    game.setFlop(firstCard + " " + secondCard + " " + thirdCard);

                    //ACTIONS ON FLOP
                    game = setActions(contentArray, i, flopActionsList, game, ActionPhase.FLOP);
                }
                if (contentArray[i].toLowerCase().contains("turn")) {
                    game.setTurn(contentArray[i].split(" ")[5].trim());

                    //ACTIONS ON TURN
                    game = setActions(contentArray, i, turnActionsList, game, ActionPhase.TURN);
                }
                if (contentArray[i].toLowerCase().contains("river")) {
                    game.setRiver(contentArray[i].split(" ")[5].trim());

                    //ACTIONS ON RIVER
                    game = setActions(contentArray, i, riverActionsList, game, ActionPhase.RIVER);
                }
            }
        }
        game.setPokerHouse(post.getPokerHouse());
        game.setSeats(seatList);

        for (int i = 0; i < contentArray.length; i++) {
            if (contentArray[i].toLowerCase().contains("shows")) {
                String user = contentArray[i].split(" ")[0].trim();
                String cardOne = contentArray[i].split(" ")[3].replace(',', ' ').trim();
                String cardTwo = contentArray[i].split(" ")[4].trim();

                game.getSeats().forEach(seat -> {
                    if(seat.getUser().equals(user)){
                        seat.setCardOne(cardOne);
                        seat.setCardTwo(cardTwo);
                    }
                });
            }
        }

        game.getSeats().sort(Comparator.comparingInt(Seat::getNumber));

        return game;
    }



    private Game setActions(String [] contentArray, int i, List<Action> actionsList, Game game, ActionPhase actionPhase) {
        int index;
        if(actionPhase == ActionPhase.PREFLOP) {
            game.setMyNick(contentArray[i + 1].split(" ")[2]);
            String myHand = contentArray[i + 1].split(" ")[5] + " " + contentArray[i + 1].split(" ")[6];
            game.setMyHand(myHand);

            index = i + 2;
        }else {
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
                        && !actions[y].toLowerCase().equals("is") && !actions[y].toLowerCase().equals("folds") && !actions[y].toLowerCase().equals("raises") && y > 0) {
                    if (actions[y].equals("[")) {
                        break;
                    } else {
                        userName = userName + " " + actions[y];
                    }
                }
                action = new Action();
                if (actions[y].toLowerCase().equals("folds")) {
                    action.setBetAmount(new BigDecimal(0));
                    action.setUserName(userName);
                    action.setActionType(ActionType.FOLD);
                    actionsList.add(action);
                } else if (actions[y].toLowerCase().equals("checks")) {
                    action.setBetAmount(new BigDecimal(0));
                    action.setUserName(userName);
                    action.setActionType(ActionType.CHECK);
                    actionsList.add(action);
                } else if (actions[y].toLowerCase().equals("calls")) {
                    action.setUserName(userName);
                    action.setActionType(ActionType.CALL);
                    String amount = actions[y + 1].replace("[", "").replace("]", "").replaceAll(",", "").trim();
                    action.setBetAmount(new BigDecimal(amount));
                    actionsList.add(action);
                } else if (actions[y].toLowerCase().equals("all-in")) {
                    action.setUserName(userName);
                    action.setActionType(ActionType.ALLIN);
                    String amount = actions[y + 2].replace("[", "").replace("]", "").replaceAll(",", "").trim();
                    action.setBetAmount(new BigDecimal(amount));
                    actionsList.add(action);
                } else if (actions[y].toLowerCase().equals("raises")) {
                    action.setUserName(userName);
                    action.setActionType(ActionType.RAISE);
                    String amount = actions[y + 1].replace("[", "").replace("]", "").replaceAll(",", "").trim();
                    action.setBetAmount(new BigDecimal(amount));
                    actionsList.add(action);
                }else if (actions[y].toLowerCase().equals("bets")) {
                    action.setUserName(userName);
                    action.setActionType(ActionType.BETS);
                    String amount = actions[y + 1].replace("[", "").replace("]", "").replaceAll(",", "").trim();
                    action.setBetAmount(new BigDecimal(amount));
                    actionsList.add(action);
                }

            }

            if(actionPhase == ActionPhase.PREFLOP){
                game.setPreFlopActions(actionsList);
            }
            if(actionPhase == ActionPhase.FLOP){
                game.setFlopActions(actionsList);
            }
            if(actionPhase == ActionPhase.TURN){
                game.setTurnActions(actionsList);
            }
            if(actionPhase == ActionPhase.RIVER){
                if(!actionsList.isEmpty())
                game.setRiverActions(actionsList);
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
