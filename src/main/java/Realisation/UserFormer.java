package Realisation;

import Models.User;
import Interfaces.IFormer;
import Interfaces.IHasher;

public class UserFormer implements IFormer<User> {
    private final OperationManager operationManager;
    private IHasher hasher;

    public UserFormer(OperationManager operationManager, IHasher hasher) {
        this.operationManager = operationManager;
        this.hasher = hasher;
    }

    @Override
    public User formObj() {
        User user = new User();

        System.out.println("Enter users name.");
        user.setName(this.operationManager.getLine());

        System.out.println("Enter users password.");
        user.setPassword(hasher.hash(this.operationManager.getLine()));

        return user;
    }

    public OperationManager getOperationManager() {
        return operationManager;
    }

    public void setHasher(IHasher hasher) {
        this.hasher = hasher;
    }
}
