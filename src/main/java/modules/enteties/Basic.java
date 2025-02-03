package modules.enteties;

import java.io.Serializable;


public interface Basic<ID extends Serializable> {
    ID getId();
    void setId(ID id);

}