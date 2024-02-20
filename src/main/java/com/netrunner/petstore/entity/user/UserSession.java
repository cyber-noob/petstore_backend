package com.netrunner.petstore.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "user_session")
public class UserSession {

    @Id
    @Column
    private String id;

    @Column
    private String user_id;

    @Column
    private Long active_expires;

    @Column
    private Long idle_expires;
}
