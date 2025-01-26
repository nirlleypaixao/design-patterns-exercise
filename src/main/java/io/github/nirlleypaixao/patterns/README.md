# Strategy design pattern exercise

The Next Unicorn is a SaaS startup which recently gained traction and validated its product. After a period of paid
closed beta, it wants to open its service to the public. It will do it by providing four different subscriptions plans:

| Plan       | Target market                | Maximum users | Price per user   | Storage in GB           | Price per GB     |
|------------|------------------------------|---------------|------------------|-------------------------|------------------|
| Free       | Prospective clients          | 1             | 0.00             | Not available           | 0                |
| Basic      | Small companies              | 5             | 30.00            | Not available           | 0                |
| Business   | Small to mid-sized companies | 100           | 70.00            | 100                     | 1.50             |
| Enterprise | Big corporations             | Unlimited     | Negotiated price | Free up to 5GB per user | Negotiated price |

Each month, Next Unicorn will charge its clients by issuing an invoice. It's up to you to implement the algorithms to
calculate the due amount based on the client's usage and plan.

## Instructions

The first step is to think how to implement the [Strategy design pattern](https://refactoring.guru/design-patterns/strategy)
based on the plans characteristics.

You should keep the API of the `Billing` class in place: its only method, `invoice()` should continue accepting a
`Contract` and an `Usage` object to calculate and return an `Invoice`.

Unit tests for the `Billing` class are in place to help you check if your implementation is working as expected. You may
add extra test cases in the `BillingTest` class as needed.

Also, assume that the `Billing` class will always receive valid `Contract` and `Usage` objects. You don't need to add
validation like checking if the number of users in `Usage` is valid according to the client's current plan. The single
responsibility of the `Billing` class is to invoice a client based on its contract and usage.

## Storage usage

The clients' users may store additional data in the tool in the Business and Enterprise plans. Business plan subscribers
may use up to 100GB per month free of charge. Additional GB consumed in this plan are charged and cost USD 1.50 per GB.

Enterprise plan clients may use up 5x the number of users in GB and also negotiate the price per GB. For example, a
company in the Enterprise plan with 1000 users will be able to use up to 5000GB of storage per month free of charge.

The following table has examples of how different clients would pay for storage usage:

| Example | Users | Plan       | Storage quota | Storage usage | Storage price | Storage cost |
|---------|-------|------------|---------------|---------------|---------------|--------------|
| 1       | 50    | Basic      | 100           | 50            | 1.50          | 0.00         |
| 2       | 70    | Basic      | 100           | 120           | 1.50          | 0.00         |
| 3       | 150   | Enterprise | 750           | 500           | 1.05          | 0.00         |
| 4       | 150   | Enterprise | 750           | 800           | 1.05          | 52.50        |

## Enterprise plan

The Enterprise plan is intended for large corporations. So, the price per user won't be available publicly. Next Unicorn
hopes to charge USD 135 per user from large corporations (with more than 10 thousand users) and USD 185 per from
corporations with 100+ users.

The Enterprise plan has all the premium features available and Next Unicorn wants to have a beefy profit margin from
these clients.

To make the plan more attractive, Next Unicorn gives 5GB of storage space per user with no extra charge for Enterprise
plan clients. So, if a client has 1000 active users, it will be able to use 5000GB of storage space. If the storage
usage exceeds this limit, it will pay a negotiated price per GB. For example, suppose that Company A is a client and
that it has 2500 users. With this number of user, Company A will have up to 12500 GB of free storage. Suppose also that 
the negotiated storage price per GB is of USD 0.91 and following usage levels for this company in the last three months:

| Month | Users | Storage quota in GB | Storage usage | Storage cost |
|-------|-------|---------------------|---------------|--------------|
| 1     | 2500  | 12500               | 10000         | 0.00         |
| 2     | 2500  | 12500               | 12500         | 0.00         |
| 3     | 2500  | 12500               | 13500         | 910.00       |
