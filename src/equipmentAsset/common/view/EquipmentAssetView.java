package equipmentAsset.common.view;

import common.view.HospitalBannerUtils;

public class EquipmentAssetView {
	
	/** =-=-=-=-=-=-=-=-=-=-=-= 최상위 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/
	
    public void equipmentAssetMenuForMaster() {
        HospitalBannerUtils.printEquipmentBanner();
        System.out.println();
        System.out.println("0\uFE0F⃣ 이전 메뉴 돌아가기");
        System.out.println("1\uFE0F⃣ 장비 정보 관리");
        System.out.println("2\uFE0F⃣ 정기 점검 관리");
        System.out.println("3\uFE0F⃣ 장비 수리/정비 관리");
        System.out.println("4\uFE0F⃣ 자산 이력 관리");
        System.out.println();
        System.out.print("\u23E9 ");
    }

    public void equipmentAssetMenu() {
        HospitalBannerUtils.printEquipmentBanner();
        System.out.println();
        System.out.println("0\uFE0F⃣ 로그아웃");
        System.out.println("1\uFE0F⃣ 장비 정보 관리");
        System.out.println("2\uFE0F⃣ 정기 점검 관리");
        System.out.println("3\uFE0F⃣ 장비 수리/정비 관리");
        System.out.println("4\uFE0F⃣ 자산 이력 관리");
        System.out.println();
        System.out.print("\u23E9 ");
    }
    
}
