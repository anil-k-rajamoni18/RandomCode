def is_not_empty(data):
    return len(data.strip()) >= 1

def validate_statecode(st_code):
    return len(st_code) == 2

def filter_data_on_statecode(inp_state_code: str) -> None:
    output_file = 'filtered.txt'
    match_count = 0
    output_lst = []
    if validate_statecode(inp_state_code):
        with open('WXL.txt', mode='r') as f:
            for line in f:
                line = line.strip()
                station_identifier = line[:3]
                station_code = line[62:64]
                station_city = line[22:62].strip()
                if is_not_empty(station_code) and station_code == inp_state_code:
                    match_count += 1
                    output_lst.append(station_identifier + " " + station_city)
        if output_lst:
            with open(output_file, mode = 'w') as f:
                f.write("\n".join(output_lst))
                f.write("\n")
                f.write(f'RECORDS MATCHING {inp_state_code}: {len(output_lst)}')
        else:
            with open(output_file, mode = 'w') as f:
                f.write(f'NO MATCHING FOUND for {inp_state_code}')
    else:
        with open(output_file, mode = 'w') as f:
            f.write(f'INVALID STATE CODE {inp_state_code}')
    print('program completed..')


if __name__ == '__main__':
    inp_state_code = input('Enter a state code to filter on: ')
    filter_data_on_statecode(inp_state_code)