export class Strategy {
    id: number;
    strategyName: string;
    userId: number;
    isRunning: boolean;
    stock: string;
    startingVol: number;
    exitProfitPercent: number;
    exitLossPercent: number;
    stdDevs: number;
    shortTime: number;


    constructor(
        id: number,
        strategyName: string,
        userId: number,
        isRunning: boolean,
        stock: string,
        startingVol: number,
        exitProfitPercent: number,
        exitLossPercent: number,
        stdDevs: number,
        shortTime: number
    ) {
        this.id = id;
        this.strategyName = strategyName;
        this.userId = userId;
        this.isRunning = isRunning;
        this.stock = stock;
        this.startingVol = startingVol;
        this.exitProfitPercent = exitProfitPercent;
        this.exitLossPercent = exitLossPercent;
        this.stdDevs = stdDevs;
        this.shortTime = shortTime;
    }
}

