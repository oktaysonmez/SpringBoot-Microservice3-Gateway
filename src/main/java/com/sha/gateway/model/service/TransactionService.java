package com.sha.gateway.model.service;

import com.google.gson.JsonElement;
import com.sha.gateway.channel.utility.RetrofitUtil;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService  extends AbstractTransactionService
{
    public List<JsonElement> getAllTransactionsOfUser(Integer userID) {
        return RetrofitUtil.callGenericBlock(transactionServiceCallable.getAllTransactionOfAuthorizedUser(userID));
    }

    @Override
    public List<JsonElement> findAll() {
        return null;
    }

    @Override
    public JsonElement save(JsonElement entity) {
        return RetrofitUtil.callGenericBlock(transactionServiceCallable.save(entity));
    }

    @Override
    public void deleteByID(Integer id) {
        RetrofitUtil.callGenericBlock(transactionServiceCallable.deleteByID(id));
    }
}
