package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;

public interface ParcelService {
    NewParcelInfo submitParcel(Parcel parcel);
}
