export class Trade {
    id: number;
    buy: boolean;
    price: number;
    size: number;
    stock: string;
    tradeDate: any;
    state: string;
    strategyId: number;
    userId: number;

    constructor(
        id: number,
        buy: boolean,
        price: number,
        size: number,
        stock: string,
        tradeDate: any,
        state: string,
        strategyId: number,
        userId: number
    ) {
        this.id = id;
        this.buy = buy;
        this.price = price;
        this.size = size;
        this.stock = stock;
        this.tradeDate = tradeDate;
        this.state = state;
        this.strategyId = strategyId;
        this.userId = userId;
    }
}

