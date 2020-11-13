package edu.gqq.funcinterface.actiontest;

import java.security.cert.CertPathValidatorException;

public class Decision {

    public Decision(DecisionStatus status, CertPathValidatorException.Reason reason) {
        this.status = status;
        this.reason = reason;
    }

    public DecisionStatus getStatus() {
        return status;
    }

    public CertPathValidatorException.Reason getReason() {
        return reason;
    }

    /**
     * 3DS decision status code
     */
    private DecisionStatus status = null;

    /**
     * Reason of the risk evaluation by issuer for inroamtional purposes
     */

    private CertPathValidatorException.Reason reason = null;

    @Override
    public String toString() {
        return "Decision{" +
            "status=" + status.toString() +
            ", reason=" + reason.toString() +
            '}';
    }

    public enum DecisionStatus {
        Enabled, Disabled
    }
}
