Collecting data with streams
===

Grouping transactions by currency in imperative style
---

```java
Map<Currency, List<Transaction>> transactionsByCurrencies =
                                                  new HashMap<>();
for (Transaction transaction : transactions) {
    Currency currency = transaction.getCurrency();
    List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
    if (transactionsForCurrency == null) {
        transactionsForCurrency = new ArrayList<>();
        transactionsByCurrencies.put(currency, transactionsForCurrency);
    }
     transactionsForCurrency.add(transaction);
}
```

Java8:

```java
Map<Currency, List<Transaction>> transactionsByCurrencies =
        transactions.stream().collect(groupingBy(Transaction::getCurrency));
```

Collectors in a nutshell
------
 *main advantages of functional-style programming over an imperative approach: you just have to formulate the result you want to obtain the “what” and not the steps you need to perform to obtain it—the “how.