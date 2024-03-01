package com.system.foodie_hub.pojo.user_management;
import com.system.foodie_hub.entity.user_management.AdminPage;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminPagePojo {
    private Integer id;
    @NotEmpty(message = "name can't be empty")
    private String fullname;

    @NotEmpty(message = " email can't be empty")
    private String email;


    private Integer contact_number;
    @NotEmpty(message = " description can't be empty")
    private String description;

    public AdminPagePojo(AdminPage adminPage){
        this.id=adminPage.getId();
        this.fullname = adminPage.getFullname();
        this.email = adminPage.getEmail();
        this.contact_number = adminPage.getContact_number();
        this.description = adminPage.getDescription();
    }


}
