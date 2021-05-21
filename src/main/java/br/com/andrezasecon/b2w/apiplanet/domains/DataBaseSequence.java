package br.com.andrezasecon.b2w.apiplanet.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
public class DataBaseSequence {

    @Id
    private String id;
    private int seq;

    public DataBaseSequence() {
    }

    public DataBaseSequence(String id, int seqNumber) {
        this.id = id;
        this.seq = seqNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seqNumber) {
        this.seq = seqNumber;
    }
}
