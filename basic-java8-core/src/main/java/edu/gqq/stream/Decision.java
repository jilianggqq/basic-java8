package edu.gqq.stream;

import java.security.cert.CertPathValidatorException;

public class Decision {

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CertPathValidatorException.Reason getReason() {
        return reason;
    }

    public void setReason(CertPathValidatorException.Reason reason) {
        this.reason = reason;
    }

    /**
     * 3DS decision status code
     */
    private String status = null;

    /**
     * Reason of the risk evaluation by issuer for inroamtional purposes
     */

    private CertPathValidatorException.Reason reason = null;
}
