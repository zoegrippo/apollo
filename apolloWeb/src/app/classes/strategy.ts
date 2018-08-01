export class Strategy {
    id: number;
    strategyName: string;
    user: any;
    onoff: boolean;
    stock: string;
    startingVol: number;
    exitProfitPercent: number;
    exitLossPercent: number;
    stdDevs: number;
    shortTime: number;


    constructor(
        id: number,
        strategyName: string,
        user: any,
        onoff: boolean,
        stock: string,
        startingVol: number,
        exitProfitPercent: number,
        exitLossPercent: number,
        stdDevs: number,
        shortTime: number
    ) {
        this.id = id;
        this.strategyName = strategyName;
        this.user = user;
        this.onoff = onoff;
        this.stock = stock;
        this.startingVol = startingVol;
        this.exitProfitPercent = exitProfitPercent;
        this.exitLossPercent = exitLossPercent;
        this.stdDevs = stdDevs;
        this.shortTime = shortTime;
    }
}

