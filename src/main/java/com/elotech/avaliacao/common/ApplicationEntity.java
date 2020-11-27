package com.elotech.avaliacao.common;

import java.io.Serializable;

public interface ApplicationEntity<ID extends Serializable> extends Serializable {

    ID getId();
}
