package com.kotmatross.shaderfixer.proxy;

import com.kotmatross.shaderfixer.utils.IncompatibleModException;

public class ClientProxy extends CommonProxy {

    @Override
    public void throwIncompatibleModException(String reason, String restart) {
        throw new IncompatibleModException(reason, restart);
    }
}
