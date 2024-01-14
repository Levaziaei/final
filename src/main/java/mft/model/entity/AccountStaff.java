package mft.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@ToString

public class AccountStaff {
    public Management management;
    public Book book;
}
