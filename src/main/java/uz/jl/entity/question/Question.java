package uz.jl.entity.question;

import lombok.*;
import org.bson.types.ObjectId;
import uz.jl.entity.base.Auditable;
import uz.jl.enums.Category;
import uz.jl.enums.Language;
import uz.jl.enums.Level;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Question extends Auditable {
    private String title;
    private List<QuestionAnswer> answers;
    private String level;
    private String language;
    private String category;
    private String createdBy;
    private int mark;

    @Builder(builderMethodName = "childBuilder")
    public Question(ObjectId id, Date createdAt, String title, List<QuestionAnswer> answers, String level, String language, String category, int mark, String createdBy) {
        super(id, createdAt);
        this.title = title;
        this.answers = answers;
        this.level = level;
        this.language = language;
        this.category = category;
        this.mark = mark;
        this.createdBy = createdBy;
    }
}
