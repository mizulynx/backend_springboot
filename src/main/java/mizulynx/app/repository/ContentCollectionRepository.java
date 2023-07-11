package mizulynx.app.repository;

import jakarta.annotation.PostConstruct;
import mizulynx.app.model.Content;
import mizulynx.app.model.ContentStatus;
import mizulynx.app.model.Type;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.ArrayList;


@Repository
public class ContentCollectionRepository {
    private final List<Content> contentList = new ArrayList<>();


        public ContentCollectionRepository(){}
        public List<Content> findAll() {
            return contentList;
        }
        public Optional<Content> findById(Integer id) {
            return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
        }
        @PostConstruct
        private void init() {
            Content c = new Content(
                    1,
                    "my first",
                    "describtion",
                    Type.ARTICLE,
                    ContentStatus.IDEA,
                    LocalDateTime.now(),
                    null,
                    ""


            );
            contentList.add(c);

        }
        public void save(Content content) {
            contentList.removeIf(c -> c.id().equals((content.id())));
        contentList.add(content);
        }
        public  boolean existById(Integer id){
            return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
        }
        public void delete(Integer id){
            contentList.removeIf(c -> c.id().equals((id)));
        }


    }
