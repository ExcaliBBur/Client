package Processing;

import Data.User;
import Interfaces.IFormer;

public class UserFormer implements IFormer<User> {
    private final OperationManager operationManager;

    public UserFormer(OperationManager operationManager) {
        this.operationManager = operationManager;
    }

    @Override
    public User formObj() {
        User user = new User();

        System.out.println("Enter users name.");
        user.setName(this.operationManager.getLine());

        System.out.println("Enter users password.");
        user.setPassword(this.operationManager.getLine());

        return user;
    }

    public OperationManager getOperationManager() {
        return operationManager;
    }
}
