import asyncio


async def find_divisibles(inrange, div_by):
    print("Range {} Divisible by {}".format(inrange, div_by))
    located = []

    for i in range(inrange):
        if i % div_by == 0:
            located.append(i)
            if i % 50000 == 0:  # just a test example ;)
                await asyncio.sleep(0.00001)

    print("Done. Range {} Divisible by {}".format(inrange, div_by))
    return located


async def two():
    print('starting two')
    await  asyncio.sleep(2)
    print('two done')
    return 2


async def four():
    print('starting four')
    await asyncio.sleep(4)
    print('four done')
    return  4


async def main():
    divs1 = loop.create_task(find_divisibles(7679304, 3445))
    divs2 = loop.create_task(find_divisibles(867324, 32546))
    divs3 = loop.create_task(find_divisibles(424, 3))

    await asyncio.wait([divs1, divs2, divs3])

    print(await asyncio.gather(two(), four()))

    return divs1, divs2, divs3


if __name__ == "__main__":
    try:
        loop = asyncio.get_event_loop()
        # loop.set_debug(1)
        d1, d2, d3 = loop.run_until_complete(main())

        print(d2.result())
    except Exception as e:
        pass
    finally:
        loop.close()
