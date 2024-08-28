package com.ericsson.cifwk.taf.koans.scenarios.test.operators;

import com.ericsson.cifwk.taf.annotations.Operator;

@Operator
public class UserMgmtOperator {

    public boolean login() {
        return true;
    }

    public String findUser(String userName) {
        return userName;
    }

    public String selectUser(String userName) {
        return "User " + userName + " selected";
    }

    public boolean promoteUser(String userName) {
        return true;
    }

}
