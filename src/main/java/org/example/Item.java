package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
public class Item {
    private int d;
    private int r;
    private int l;

    @Override
    public String toString() {
        return "Item{" +
                "d=" + d +
                ", l=" + l +
                '}';
    }
}
