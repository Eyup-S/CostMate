package com.falcon.CostMate.Services;

import com.falcon.CostMate.Entity.Group;
import com.falcon.CostMate.Entity.UserAddedCategory;
import com.falcon.CostMate.Repositories.GroupRepository;
import com.falcon.CostMate.Repositories.UserAddedCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddedCategoryService {

    @Autowired
    private UserAddedCategoryRepository userAddedCategoryRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CurrentUserService currentUserService;

    public List<UserAddedCategory> getAllUserAddedCategories(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found: ID " + groupId));

        /*
        AppUser currentUser = currentUserService.getCurrentUser();
        if (!group.isMember(currentUser)) {
            throw new RuntimeException("User is not a member of this group.");
        }
        */

        return userAddedCategoryRepository.findByCategoryGroup(group);
    }

    public UserAddedCategory addUserAddedCategory(String categoryName, Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found: ID " + groupId));

        /*
        AppUser currentUser = currentUserService.getCurrentUser();
        if (!group.isMember(currentUser)) {
            throw new RuntimeException("User is not a member of this group.");
        }
        */

        UserAddedCategory userAddedCategory = new UserAddedCategory();
        userAddedCategory.setName(categoryName);
        userAddedCategory.setCategoryGroup(group);
        //userAddedCategory.setCreatedBy(currentUser);

        return userAddedCategoryRepository.save(userAddedCategory);
    }

    public UserAddedCategory getUserAddedCategoryById(Long cid) {
        return userAddedCategoryRepository.findById(cid)
                .orElseThrow(() -> new RuntimeException("Custom category not found: ID " + cid));
    }

    public UserAddedCategory updateUserAddedCategory(Long cid, UserAddedCategory updatedUserAddedCategory) {
        UserAddedCategory existingUserAddedCategory = userAddedCategoryRepository.findById(cid)
                .orElseThrow(() -> new RuntimeException("Custom category not found: ID " + cid));

        // Update fields
        existingUserAddedCategory.setName(updatedUserAddedCategory.getName());
        // If you want to move it to a different group or update createdBy, you'd do that here as well.

        return userAddedCategoryRepository.save(existingUserAddedCategory);
    }

    public void deleteUserAddedCategory(Long cid) {
        UserAddedCategory userAddedCategory = userAddedCategoryRepository.findById(cid)
                .orElseThrow(() -> new RuntimeException("Custom category not found: ID " + cid));

        userAddedCategoryRepository.delete(userAddedCategory);
    }
}