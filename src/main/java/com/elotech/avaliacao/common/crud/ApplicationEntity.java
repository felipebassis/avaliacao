package com.elotech.avaliacao.common.crud;

import java.io.Serializable;

public interface ApplicationEntity<ID extends Serializable> extends Serializable {

    ID getId();
}
