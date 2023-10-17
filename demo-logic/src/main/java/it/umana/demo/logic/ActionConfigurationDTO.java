package it.umana.demo.logic;

import java.io.Serializable;

/**
 */

public class ActionConfigurationDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7614081417556865212L;

    /**
     * iCodiProc
     */
    private String iCodiProc;

    /**
     * iCodiServ
     */
    private String iCodiServ;

    /**
     * iDestProc
     */
    private String iDestProc;

    /**
     * nomePlugin
     */
    private String nomePlugin;

    /**
     * canSend
     */
    private Boolean canSend;

    /**
     * knowladgeQueueName
     */
    private String knowledgeQueueName;

    /**
     * KnowladgeErrorQueueName
     */
    private String knowledgeErrorQueueName;

    /**
     * maxRetryError
     */
    private int maxRetryError;

    /**
     * classpath
     */
    private String classpath;

    /**
     * retryQueueName
     */
    private String retryQueueName;

    /**
     * refuseQueueName
     */
    private String refuseQueueName;

    /**
     * th constructor
     */
    public ActionConfigurationDTO() {

    }

    /**
     * @param classpath the classpath to set
     */
    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    /**
     * @return the iCodiProc
     */
    public String getICodiProc() {
        return iCodiProc;
    }

    /**
     * @param iCodiProc the iCodiProc to set
     */
    public void setICodiProc(String iCodiProc) {
        this.iCodiProc = iCodiProc;
    }

    /**
     * @return the nomePlugin
     */
    public String getNomePlugin() {
        return nomePlugin;
    }

    /**
     * @param nomePlugin the nomePlugin to set
     */
    public void setNomePlugin(String nomePlugin) {
        this.nomePlugin = nomePlugin;
    }

    /**
     * @return the iDestProc
     */
    public String getiDestProc() {
        return iDestProc;
    }

    /**
     * @param iDestProc the iDestProc to set
     */
    public void setIDestProc(String iDestProc) {
        this.iDestProc = iDestProc;
    }

    /**
     * @return the canSend
     */
    public Boolean getCanSend() {
        return canSend;
    }

    /**
     * @param canSend the canSend to set
     */
    public void setCanSend(Boolean canSend) {
        this.canSend = canSend;
    }


    /**
     * @return the knowledgeQueueName
     */
    public String getKnowledgeQueueName() {
        return knowledgeQueueName;
    }

    /**
     * @param knowledgeQueueName the knowledgeQueueName to set
     */
    public void setKnowledgeQueueName(String knowledgeQueueName) {
        this.knowledgeQueueName = knowledgeQueueName;
    }

    /**
     * @return the knowledgeErrorQueueName
     */
    public String getKnowledgeErrorQueueName() {
        return knowledgeErrorQueueName;
    }

    /**
     * @param knowledgeErrorQueueName the knowledgeErrorQueueName to set
     */
    public void setKnowledgeErrorQueueName(String knowledgeErrorQueueName) {
        this.knowledgeErrorQueueName = knowledgeErrorQueueName;
    }

    /**
     * @return the maxRetryError
     */
    public int getMaxRetryError() {
        return maxRetryError;
    }

    /**
     * @param maxRetryError the maxRetryError to set
     */
    public void setMaxRetryError(int maxRetryError) {
        this.maxRetryError = maxRetryError;
    }

    /**
     * @return the retryQueueName
     */
    public String getRetryQueueName() {
        return retryQueueName;
    }

    /**
     * @return the classpath
     */
    public String getClasspath() {
        return classpath;
    }

    /**
     * @param retryQueueName the retryQueueName to set
     */
    public void setRetryQueueName(String retryQueueName) {
        this.retryQueueName = retryQueueName;
    }

    /**
     * @return the refuseQueueName
     */
    public String getRefuseQueueName() {
        return refuseQueueName;
    }

    /**
     * @param refuseQueueName the refuseQueueName to set
     */
    public void setRefuseQueueName(String refuseQueueName) {
        this.refuseQueueName = refuseQueueName;
    }

    /**
     * @return the iCodiServ
     */
    public String getiCodiServ() {
        return iCodiServ;
    }

    /**
     * @param iCodiServ the iCodiServ to set
     */
    public void setiCodiServ(String iCodiServ) {
        this.iCodiServ = iCodiServ;
    }


}