package uz.jl.entity;

import lombok.*;
import uz.jl.entity.base.Auditable;
import uz.jl.entity.base.BaseGenericEntity;
import uz.jl.entity.question.Question;
import uz.jl.enums.Category;
import uz.jl.enums.Level;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Quiz extends Auditable implements BaseGenericEntity {
    private int mark;
    private Level level;
    private Category category;
    private List<Question> questions;
    private boolean closed;
    private static int size;
    /**
     * in minutes
     */
    private int duration;

}
