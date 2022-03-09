package uz.jl.entity.question;


import lombok.*;
import org.bson.types.ObjectId;
import uz.jl.entity.base.Auditable;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class QuestionAnswer extends Auditable {
    private String answer;
    private boolean right;

    @Builder(builderMethodName = "childBuilder")
    public QuestionAnswer(ObjectId id, Date createdAt, String answer, boolean right) {
        super(id, createdAt);
        this.answer = answer;
        this.right = right;
    }
}
