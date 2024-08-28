package com.ericsson.taf.workshop.scenario.common;

import com.ericsson.cifwk.taf.TafTestContext;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.google.common.io.BaseEncoding;

import static com.ericsson.taf.workshop.scenario.common.TestSteps.DataSources.NODES;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is intentionally poorly written Test Steps since it contains obscure logic, some test steps are hanging and some
 * are failing. Your objective is to find out which test steps are actually failing
 */
public class BadTestSteps {
    public static final String STEP_XV = "STEP_XV";
    public static final String STEP_XA = "STEP_XA";
    public static final String STEP_FV = "STEP_FV";
    public static final String STEP_GR = "STEP_GR";
    public static final String STEP_YF = "STEP_YF";
    public static final String STEP_FM = "STEP_FM";
    public static final String STEP_OO = "STEP_OO";
    public static final String STEP_DS = "STEP_DS";
    public static final String STEP_PD = "STEP_PD";
    public static final String STEP_ZT = "STEP_ZT";
    public static final String STEP_KR = "STEP_KR";
    public static final String STEP_IP = "STEP_IP";
    public static final String STEP_PL = "STEP_PL";
    public static final String STEP_TC = "STEP_TC";
    public static final String STEP_TS = "STEP_TS";
    public static final String STEP_RP = "STEP_RP";
    public static final String STEP_ML = "STEP_ML";

    @TestStep(id = STEP_XV)
    public void stepXv(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_XV)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_XV)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_XA)
    public void stepXa(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_XA)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_XA)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_FV)
    public void stepFv(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_FV)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_FV)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_GR)
    public void stepGr(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_GR)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_GR)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_YF)
    public void stepYf(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_YF)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_YF)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_FM)
    public void stepFm(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_FM)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_FM)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_OO)
    public void stepOo(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_OO)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_OO)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_DS)
    public void stepDs(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_DS)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_DS)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_PD)
    public void stepPd(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_PD)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_PD)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_KR)
    public void stepKr(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_KR)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_KR)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_IP)
    public void stepIp(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_IP)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_IP)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    @TestStep(id = STEP_ZT)
    public void stepZt(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();

        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_ZT)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_ZT)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_PL)
    public void stepPl(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_PL)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_PL)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_TC)
    public void stepTc(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_TC)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_TC)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_TS)
    public void stepTs(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_TS)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_TS)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_RP)
    public void stepRp(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_RP)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_RP)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TestStep(id = STEP_ML)
    public void stepMl(@Input(NODES) Node node) {
        Integer vUser = TafTestContext.getContext().getVUser();
        if (new String(BaseEncoding.base64().decode("U1RFUF9PTw==")).equals(STEP_ML)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("Mjk=")).equals(vUser.toString())) {
            throw new VerySpecialException();
        }

        if (new String(BaseEncoding.base64().decode("U1RFUF9aVA==")).equals(STEP_ML)
                && new String(BaseEncoding.base64().decode("U0dTTi0xNEI=")).equals(node.getNetworkElementId())
                && new String(BaseEncoding.base64().decode("MQ==")).equals(vUser.toString())) {
            try {
                Thread.sleep(999999999);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean assertHangingTestStep(String testStepName, String nodeNetworkElementId, Integer vUser) {
        assertThat(testStepName.toLowerCase()).overridingErrorMessage("Wrong Test Step Name").contains(new String(BaseEncoding.base64().decode("enQ=")));
        assertThat(nodeNetworkElementId.toLowerCase()).overridingErrorMessage("Wrong Network Element Id").contains(new String(BaseEncoding.base64().decode("c2dzbi0xNA==")));
        assertThat(vUser.toString()).overridingErrorMessage("Wrong vUser").contains(new String(BaseEncoding.base64().decode("MQ==")));
        return true;
    }


    public boolean assertFailingTestStep(String testStepName, String nodeNetworkElementId, Integer vUser) {
        assertThat(testStepName.toLowerCase()).overridingErrorMessage("Wrong Test Step Name").contains(new String(BaseEncoding.base64().decode("b28=")));
        assertThat(nodeNetworkElementId.toLowerCase()).overridingErrorMessage("Wrong Network Element Id").contains(new String(BaseEncoding.base64().decode("c2dzbi0xNA==")));
        assertThat(vUser.toString()).overridingErrorMessage("Wrong vUser").contains("2" + new String(BaseEncoding.base64().decode("OQ==")));
        return true;
    }

}
