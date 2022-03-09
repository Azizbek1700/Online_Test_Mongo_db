package uz.jl.dto.question;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import uz.jl.dto.GenericDto;
import uz.jl.entity.question.QuestionAnswer;

import java.util.Date;
import java.util.List;

/**
 * @author Botirov Najmiddin, Mon 00:00. 31/01/2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto extends GenericDto {
    private String title;
    private List<QuestionAnswer> answers;
    private String level;
    private String language;
    private String category;
    private String createdBy;
    private Date createdAt;
    private int mark;

    public QuestionDto(String id, Date createdAt, String title, List<QuestionAnswer> answers, String level, String language, String category, int mark) {
        super(id);
        this.createdAt = createdAt;
        this.title = title;
        this.answers = answers;
        this.level = level;
        this.language = language;
        this.category = category;
        this.mark = mark;
    }
}
