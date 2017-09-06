package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;

import java.math.BigDecimal;
import java.sql.Time;

public class TagResponse {
    @JsonProperty
    Integer id;

    @JsonProperty
    Integer receiptId;

    @JsonProperty
    String tag;

    public TagResponse(TagsRecord dbRecord) {
        this.receiptId = dbRecord.getReceiptId();
        this.tag = dbRecord.getTag();
        this.id = dbRecord.getId();
    }
}
