package com.falcon.CostMate.Services;

import com.falcon.CostMate.Entity.AppUser;
import com.falcon.CostMate.Entity.Group;
import com.falcon.CostMate.Entity.UserAddedCategory;
import com.falcon.CostMate.Repositories.GroupRepository;
import com.falcon.CostMate.Repositories.UserAddedCategoryRepository;
import com.falcon.CostMate.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAddedCategoryService {

    @Autowired
    private UserAddedCategoryRepository userAddedCategoryRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private AppUserRepository userRepository;

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
        /*
        if (item.getCreatedBy() != null) {
            Optional<AppUser> createdBy = userRepository.findById(item.getCreatedBy().getUid());
            if (createdBy.isPresent()) {
                item.setCreatedBy(createdBy.get());
            } else {
                throw new RuntimeException("User not found: ID " + item.getCreatedBy().getUid());
            }
        }
        try {
            String name = item.getName();
            Long groupId = item.getCategoryGroup().getGid();
            Group group = groupRepository.findById(groupId)
                    .orElseThrow(() -> new RuntimeException("Group not found: ID " + groupId));
            if (userAddedCategoryRepository.findByNameAndCategoryGroup(name, group).isPresent()) {
                throw new RuntimeException("Category already exists in this group.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error saving UserAddedCategory: " + e.getMessage());
        }
        */
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found: ID " + groupId));

        //AppUser currentUser = currentUserService.getCurrentUser();
        //if (!group.isMember(currentUser)) {
        //    throw new RuntimeException("User is not a member of this group.");
        //}

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