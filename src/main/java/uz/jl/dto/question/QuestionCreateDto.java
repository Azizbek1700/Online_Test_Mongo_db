package uz.jl.dto.question;

import lombok.*;
import org.bson.types.ObjectId;
import uz.jl.dto.BaseGenericDto;
import uz.jl.entity.question.QuestionAnswer;

import java.util.Date;
import java.util.List;

/**
 * @author Botirov Najmiddin, Sun 23:59. 30/01/2022
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class QuestionCreateDto implements BaseGenericDto {
    private String title;
    private List<QuestionAnswer> answers;
    private String level;
    private String language;
    private String category;
    private String createdBy;
    private int mark;

    @Builder(builderMethodName = "childBuilder")
    public QuestionCreateDto(ObjectId id, Date createdAt, String title, List<QuestionAnswer> answers, String level, String language, String category, int mark, String createdBy) {
        this.title = title;
        this.answers = answers;
        this.level = level;
        this.language = language;
        this.category = category;
        this.mark = mark;
        this.createdBy = createdBy;
    }
}
