package controllers;

import api.CreateReceiptRequest;
import api.ReceiptResponse;
import api.TagResponse;
import dao.TagDao;
import generated.tables.records.TagsRecord;
import generated.tables.records.ReceiptsRecord;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("/tags")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;

    public TagController(TagDao tags) {
        this.tags = tags;
    }

    @PUT
    @Path("/{tag}")
    public Integer toggleTag(@PathParam("tag") String tagName, Integer receiptId) {
        return tags.insert(receiptId, tagName);

    }

    @GET
    @Path("/{tag}")
    public List<ReceiptResponse> getTags(@PathParam("tag") String tagName) {
        //return tags.getAllReceipts(tagName);

        List<ReceiptsRecord> receiptRecords = tags.getAllReceipts(tagName);
        return receiptRecords.stream().map(ReceiptResponse::new).collect(toList());
    }

    @GET
    @Path("/receipt/{receipt}")
    public List<TagResponse> getTagsForReceipt(@PathParam("receipt") String receiptId) {
        List<TagsRecord> tagRecords = tags.getTags(Integer.parseInt(receiptId));
        return tagRecords.stream().map(TagResponse::new).collect(toList());
    }
}
