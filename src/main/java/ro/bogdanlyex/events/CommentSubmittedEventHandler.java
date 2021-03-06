package ro.bogdanlyex.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.bogdanlyex.entities.Comment;
import ro.bogdanlyex.repositories.CommentRepository;

@Service
@Transactional
public class CommentSubmittedEventHandler implements ApplicationListener<CommentSubmitted> {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void onApplicationEvent(CommentSubmitted commentSubmitted) {
        Comment comment = commentRepository.save(commentSubmitted.getComment());
        applicationEventPublisher.publishEvent(new CommentSaved(this, comment));

    }
}
