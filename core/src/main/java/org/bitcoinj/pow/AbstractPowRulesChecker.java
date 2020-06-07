package org.bitcoinj.pow;

import org.bitcoinj.chain.AbstractBlockChain;
import org.bitcoinj.chain.StoredBlock;
import org.bitcoinj.core.*;
import org.bitcoinj.exception.VerificationException;
import org.bitcoinj.msg.protocol.Block;
import org.bitcoinj.params.NetworkParameters;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.exception.BlockStoreException;

import java.math.BigInteger;

public abstract class AbstractPowRulesChecker {

    protected NetworkParameters networkParameters;

    public AbstractPowRulesChecker(NetworkParameters networkParameters) {
        this.networkParameters = networkParameters;
    }

    public abstract void checkRules(StoredBlock storedPrev, Block nextBlock, BlockStore blockStore,
                                    AbstractBlockChain blockChain) throws VerificationException, BlockStoreException;

    public static boolean hasEqualDifficulty(Block prevBlock, Block nextBlock) {
        return prevBlock.getDifficultyTarget() == nextBlock.getDifficultyTarget();
    }

    public static boolean hasEqualDifficulty(long a, BigInteger b) {
        return a == Utils.encodeCompactBits(b);
    }

}
