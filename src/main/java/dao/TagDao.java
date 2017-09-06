package dao;

import generated.tables.records.TagsRecord;
import generated.tables.records.ReceiptsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.TAGS;
import static generated.Tables.RECEIPTS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public Integer insert(Integer rid, String tag) {
        TagsRecord tagExists = dsl.selectFrom(TAGS).where(TAGS.RECEIPT_ID.eq(rid)).and(TAGS.TAG.eq(tag)).fetchAny();
        if (tagExists == null) {
            TagsRecord tagsRecord = dsl.insertInto(TAGS, TAGS.RECEIPT_ID, TAGS.TAG)
                    .values(rid, tag)
                    .returning(TAGS.ID)
                    .fetchOne();
            checkState(tagsRecord != null && tagsRecord.getId() != null, "Insert failed");
            return 2;
        }
        else {
            dsl.deleteFrom(TAGS)
                    .where(TAGS.ID.eq(tagExists.getId()));
            return 3;
        }

    }

//    public List<ReceiptsRecord> getAllReceipts(String tag) {
//        List<TagsRecord> receiptList = dsl.selectFrom(TAGS).where(TAGS.TAG.eq(tag)).fetch();
//        List<ReceiptsRecord> returnReceipts = dsl.selectFrom(RECEIPTS).where(RECEIPTS.ID.in(receiptList)).fetch();
//        return returnReceipts;
//    }

    public List<ReceiptsRecord> getAllReceipts(String tag) {
        List<TagsRecord> receiptList = dsl.selectFrom(TAGS).where(TAGS.TAG.eq(tag)).fetch();
        //String receiptString = "TEST " + receiptList.size() + " ";
        List<ReceiptsRecord> returnReceipts = new ArrayList<>();
        for (TagsRecord record : receiptList)
        {
            returnReceipts.add(dsl.selectFrom(RECEIPTS).where(RECEIPTS.ID.eq(record.getReceiptId())).fetchOne());
        }
        return returnReceipts;

        //List<ReceiptsRecord> returnReceipts = dsl.selectFrom(RECEIPTS).where(RECEIPTS.ID.in(receiptList)).fetch();
//        receiptString += "--  NumReceipts=" + returnReceipts.size() + "--";
//        for (ReceiptsRecord receipt : returnReceipts)
//        {
//            receiptString += receipt.getMerchant();
//            receiptString += " ";
//        }
//        return receiptString;
    }

    public List<TagsRecord> getAllTags()  {
        return dsl.selectFrom(TAGS).fetch();
    }
}
