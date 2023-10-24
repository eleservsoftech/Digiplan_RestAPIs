package com.digiplan.servicesImpl;

import com.digiplan.repositories.CommentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class CommentServiceImplTests {

    @InjectMocks
    private CommentServiceImpl commentServiceImpl;

    @Mock
    private CommentRepository commentRepository;

  /*  @Test
    public void test_getComment() {
        Comment comment = new Comment(1, "1802071938", "Karan", "General", "approved", null);
        Optional<Comment> retrievedData = Optional
                .of(new Comment(1, "1802071938", "Suraj", "General", "not approved", null));
        Integer id = 1;
        when(commentRepository.findById(id)).thenReturn(retrievedData);
        if (retrievedData.isPresent())
            when(commentRepository.getById(id)).thenReturn(comment);
        assertEquals(id, commentServiceImpl.getComment(id).getId());
    } */

//    @Test
//    public void test_getAllComments() {
//        List<Comment> comment = new ArrayList<>();
//        comment.add(new Comment(1, "1802071938", "Karan", "General", "approved", null));
//        comment.add(new Comment(2, "1802071934", "Maran", "General", "approved", null));
//        comment.add(new Comment(3, "1802071935", "Haran", "General", "approved", null));
//        when(commentRepository.findAll()).thenReturn(comment);
//        assertEquals(3, commentServiceImpl.getAllComments().size());
//    }

 /*   @Test
    public void test_addComment() {
        Comment comment = new Comment(1, "1802071938", "Karan", "General", "approved", null);
        when(commentRepository.saveAndFlush(comment)).thenReturn(comment);
        assertEquals(comment, commentServiceImpl.addComment(comment));
    } */

//    @Test
//    public void test_updateComment() {
//        Comment comment = new Comment(1, "1802071938", "Karan", "General", "approved", null);
//        Optional<Comment> retrievedData = Optional
//                .of(new Comment(1, "1802071938", "Suraj", "General", "not approved", null));
//        Integer id = 1;
//        when(commentRepository.findById(id)).thenReturn(retrievedData);
//        if (retrievedData.isPresent())
//            when(commentRepository.saveAndFlush(comment)).thenReturn(comment);
//        assertEquals(comment, commentServiceImpl.updateComment(id, comment));
//    }

    @Test
    public void test_deleteComment() {
        Integer id = 1;
        commentServiceImpl.deleteComment(id);
        verify(commentRepository, times(1)).deleteById(id);
    }

}
