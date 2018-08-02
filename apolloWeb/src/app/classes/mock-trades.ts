import { Trade } from './trade';

export const TRADES: Trade[] = [
    new Trade(
        1,
        true,
        125,
        100,
        'GOOG',
        '1:45PM',
        'FILLED',
        1,
        1
    ),
    new Trade(
        2,
        false,
        200,
        100,
        'GOOG',
        '1:50PM',
        'FILLED',
        1,
        1
    )
];

