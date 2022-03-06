package com.bmc.userservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Users")
public class UserEntity {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dob;
    private String mobile;
    private String emailId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdDate;
}
