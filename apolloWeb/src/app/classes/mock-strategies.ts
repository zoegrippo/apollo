import { Strategy } from './strategy';

export const STRATEGY_NAMES: string[] = [
    // 'Two Moving Averages',
    // 'Price Breakout,'
    'Bollinger Bands'
];

export const STRATEGIES: Strategy[] = [
    new Strategy(
        1,
        'Bollinger Bands',
        1,
        true,
        'AAPL',
        1000,
        .02,
        .01,
        2,
        30
    ),
    new Strategy(
        2,
        'Bollinger Bands',
        1,
        true,
        'MSFT',
        500,
        .02,
        .01,
        2,
        20
    )
];

