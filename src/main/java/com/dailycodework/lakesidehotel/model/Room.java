package com.dailycodework.lakesidehotel.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomType;
    private BigDecimal roomPrice;
    private boolean isBooked=false;
//fetch = FetchType.LAZY：这部分指定了关系的获取策略。在这种情况下，设置为LAZY获取。懒加载意味着关联的BookedRoom实体只有在被显式访问时才会从数据库中加载。这有助于提高性能，特别是在处理大型数据集时，因为它避免了在可能不需要时加载相关实体。
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<BookedRoom> bookings;
    public Room() {
        this.bookings=new ArrayList<>();
    }

    public void addBooking(BookedRoom booking){
    if (bookings==null){
        bookings=new ArrayList<>();
    }
    bookings.add(booking);
    booking.setRoom(this);
    }
}
