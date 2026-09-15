package org.ais.jcash.workflow;

public final class CmsTxnStatus {
    public static final String DRAFT = "DRAFT";
    public static final String PENDING_AUTH = "PENDING_AUTH";
    public static final String AUTHORIZED = "AUTHORIZED";
    public static final String SCHEDULED = "SCHEDULED";
    public static final String RELEASED = "RELEASED";
    public static final String PAID = "PAID";
    public static final String FAILED = "FAILED";
    public static final String STOPPED = "STOPPED";
    public static final String AWAITING_SIGNATURE = "AWAITING_SIGNATURE";
    public static final String INSTRUCTION_QUEUED = "INSTRUCTION_QUEUED";
    public static final String PRINTED = "PRINTED";

    private CmsTxnStatus() {}
}
