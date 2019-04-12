package com.blockchain;

import java.util.Date;

public class Block
{

    public String hash;
    public String previousHash;
    private String data;// Our data will be a simple message
    private long timestamp;// milliseconds
    int nonce;

    // Block Constructor

    public Block(String data, String previousHash)
    {
        super();
        this.previousHash = previousHash;
        this.data = data;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash()
    {

        String calculatedHash = StringUtil.applySha256(previousHash
                + Long.toString(timestamp)
                + Integer.valueOf(nonce)
                + data);
        return calculatedHash;
    }

    public void mineBlock(int difficulty)
    {
        System.out.println("mineBlock "+nonce);
        String target = new String(new char[difficulty]).replace('\0', '0'); // Create a string with difficulty * "0"
        while (!hash.substring(0, difficulty).equals(target))
        {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash + "   nonce!!! : "+nonce);
    }

}
