package com.javarush.jira.profile.internal.web;

import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.common.util.JsonUtil;
import com.javarush.jira.login.User;
import com.javarush.jira.login.UserTo;
import com.javarush.jira.login.internal.web.UserController;
import com.javarush.jira.profile.ProfileTo;
import com.javarush.jira.profile.internal.ProfileMapper;
import com.javarush.jira.profile.internal.ProfileRepository;
import com.javarush.jira.profile.internal.model.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.javarush.jira.login.internal.web.UserTestData.*;
import static com.javarush.jira.profile.internal.web.ProfileRestController.REST_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private ProfileRepository repository;
    @Autowired
    ProfileMapper mapper;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getUsers() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getUsersUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

//    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void update(@Valid @RequestBody ProfileTo profileTo, @AuthenticationPrincipal AuthUser authUser) {
//        super.update(profileTo, authUser.id());
//    }
    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        Profile profileBefore = repository.getExisted(ADMIN_ID);
        ProfileTo profileTo = mapper.toTo(ProfileTestData.getUpdated(ADMIN_ID));
        profileTo.setMailNotifications(null);

        perform( MockMvcRequestBuilders.put( REST_URL )
                .contentType( MediaType.APPLICATION_JSON )
                .content(JsonUtil.writeValue(profileTo))).andExpect(status().isNoContent());

//                .content(  )
                //.andDo( print() )
//                .andExpect( status().isNoContent() );


//        User dbUserBefore = repository.getExistedByEmail(USER_MAIL);
//        UserTo updatedTo = mapper.toTo(getUpdated());
//        updatedTo.setPassword(null);
//        perform(MockMvcRequestBuilders.put(UserController.REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonWithPassword(updatedTo, updatedTo.getPassword())))
//                .andDo(print())
//                .andExpect(status().isNoContent());
//
//        User dbUserAfter = repository.getExistedByEmail(USER_MAIL);
//        assertEquals(dbUserBefore.getPassword(), dbUserAfter.getPassword(), "user's password must not be changed");
//        User updated = getUpdated();
//        updated.setRoles(dbUserBefore.getRoles());   // user's roles must not be changed
//        USER_MATCHER.assertMatch(dbUserAfter, updated);
    }


}