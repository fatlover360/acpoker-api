package com.acpoker.acpokerapi.service;

import com.acpoker.acpokerapi.entity.Cards;
import com.acpoker.acpokerapi.entity.Comment;
import com.acpoker.acpokerapi.entity.Post;
import com.acpoker.acpokerapi.model.Game;
import com.acpoker.acpokerapi.model.PostDTO;
import com.acpoker.acpokerapi.model.Seat;
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
import java.util.List;

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

        return postDTO;
    }

    public Game findHandByPostId(Integer id) {

        Post post = postRepository.findPostById(id);
        String content = post.getContent();
        Game game = new Game();
        List<Seat> seatList = new ArrayList<>();

        String[] contentArray = content.split("\n");

        for (int i = 0; i < contentArray.length; i++) {
            if (contentArray[i].toLowerCase().contains("seat")) {
                if (contentArray[i].contains(":")) {
                    Seat seat = new Seat();
                    seat.setNumber(Integer.valueOf(contentArray[i].split(" ")[1].substring(0, 1)));
                    seat.setUser(contentArray[i].split(" ")[2]);
                    seat.setChips(new BigDecimal(contentArray[i].split(" ")[4].replaceAll(",", "")));
                    seatList.add(seat);
                } else {
                    game.setButtonSeat(Integer.valueOf(contentArray[i].split(" ")[1]));
                }
            }

            if(contentArray[i].toLowerCase().contains("blinds") && i > 10) {
                game.setBigBlind(new BigDecimal(contentArray[i].split("\\(")[1].split("-")[0].split("/")[0].replace(".", "")));
                game.setSmallBlind(new BigDecimal(contentArray[i].split("\\(")[1].split("-")[0].split("/")[1].replace(",","").trim()));
                game.setAnte(new BigDecimal(contentArray[i].split("\\(")[1].split("-")[1].replace(")", "").trim()));
            }

            if (contentArray[i].toLowerCase().contains("*")) {
                if (contentArray[i].toLowerCase().contains("flop")) {
                    String firstCard = contentArray[i].split(" ")[5].replace(',', ' ').trim();
                    String secondCard = contentArray[i].split(" ")[6].replace(',', ' ').trim();
                    String thirdCard = contentArray[i].split(" ")[7].replace(',', ' ').trim();
                    game.setFlop(firstCard + " " + secondCard + " " + thirdCard);
                }
                if (contentArray[i].toLowerCase().contains("turn")) {
                    game.setTurn(contentArray[i].split(" ")[5].trim());
                }
                if (contentArray[i].toLowerCase().contains("river")) {
                    game.setRiver(contentArray[i].split(" ")[5].trim());
                }
            }
        }
        game.setSeats(seatList);

       /* for (int i = 0; i < contentArray.length; i++) {
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
        }*/

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
