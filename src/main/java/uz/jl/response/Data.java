package uz.jl.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Axmadjonov Eliboy, Tue 3:57 PM,1/25/2022
 */
@Getter
@Setter
public class Data<B> {
    private final B data;
    private final Integer total;

    public Data(B data) {
        this(data, 0);
    }

    public Data(B data, Integer total) {
        this.data = data;
        this.total = total;
    }


}
