package ChatService.Chat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import net.ravendb.client.documents.changes.DocumentChange;
import org.springframework.data.annotation.TypeAlias;
@Data
@Entity
@TypeAlias("ChatMessage")
public class Chat {

    @Id
    private String id;
    private String message;
    private String sender;


}
