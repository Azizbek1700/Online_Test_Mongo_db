package uz.jl.entity.base;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Auditable implements BaseGenericEntity {

    @BsonProperty(value = "_id")
    private ObjectId id;

    private Date createdAt = new Date();

    public Auditable(ObjectId id) {
        this.id = id;
    }
}
