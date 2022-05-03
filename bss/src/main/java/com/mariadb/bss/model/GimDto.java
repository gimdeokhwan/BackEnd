package com.mariadb.bss.model;

import com.mariadb.bss.entity.Gim;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GimDto{
    private int resultCode;
    private Gim gim;
}
