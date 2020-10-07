package com.brianyi.domain;

/**
 * TODO
 *
 * @author ahao 2020-10-06
 */
//json 转换使用
public class ResponseBlockData {
    private int id;

    private String projectId;

    private String txHash;

    private String primaryKey;

    private int blockNumber;

    private String blockHash;

    private Long createTime;

    private String chaincodeName;

    private int status;

    private String creatorMspId;

    private String endorserMspId;

    private String type;

    private String readWriteSet;

    private String validationCode;

    private String creatorId;

    private String creatorNonce;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setProjectId(String projectId){
        this.projectId = projectId;
    }
    public String getProjectId(){
        return this.projectId;
    }
    public void setTxHash(String txHash){
        this.txHash = txHash;
    }
    public String getTxHash(){
        return this.txHash;
    }
    public void setPrimaryKey(String primaryKey){
        this.primaryKey = primaryKey;
    }
    public String getPrimaryKey(){
        return this.primaryKey;
    }
    public void setBlockNumber(int blockNumber){
        this.blockNumber = blockNumber;
    }
    public int getBlockNumber(){
        return this.blockNumber;
    }
    public void setBlockHash(String blockHash){
        this.blockHash = blockHash;
    }
    public String getBlockHash(){
        return this.blockHash;
    }
    public void setCreateTime(Long createTime){
        this.createTime = createTime;
    }
    public Long getCreateTime(){
        return this.createTime;
    }
    public void setChaincodeName(String chaincodeName){
        this.chaincodeName = chaincodeName;
    }
    public String getChaincodeName(){
        return this.chaincodeName;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setCreatorMspId(String creatorMspId){
        this.creatorMspId = creatorMspId;
    }
    public String getCreatorMspId(){
        return this.creatorMspId;
    }
    public void setEndorserMspId(String endorserMspId){
        this.endorserMspId = endorserMspId;
    }
    public String getEndorserMspId(){
        return this.endorserMspId;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setReadWriteSet(String readWriteSet){
        this.readWriteSet = readWriteSet;
    }
    public String getReadWriteSet(){
        return this.readWriteSet;
    }
    public void setValidationCode(String validationCode){
        this.validationCode = validationCode;
    }
    public String getValidationCode(){
        return this.validationCode;
    }
    public void setCreatorId(String creatorId){
        this.creatorId = creatorId;
    }
    public String getCreatorId(){
        return this.creatorId;
    }
    public void setCreatorNonce(String creatorNonce){
        this.creatorNonce = creatorNonce;
    }
    public String getCreatorNonce(){
        return this.creatorNonce;
    }
}