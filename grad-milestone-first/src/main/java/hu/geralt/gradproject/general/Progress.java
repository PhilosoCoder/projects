package hu.geralt.gradproject.general;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Progress<T> {

    private T data;

    public Progress(T data) {
        this.data = data;
    }

}