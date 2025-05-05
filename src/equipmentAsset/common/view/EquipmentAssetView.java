package equipmentAsset.common.view;

public class EquipmentAssetView {
	
	/** =-=-=-=-=-=-=-=-=-=-=-= 최상위 메뉴 =-=-=-=-=-=-=-=-=-=-=-= **/
	
    public void equipmentAssetMenu() {
        System.out.println("------ 자산 관리 ------");
        System.out.println("0. 이전 메뉴 돌아가기");
        System.out.println("1. 장비 정보 관리");
        System.out.println("2. 정기 점검 관리");
        System.out.println("3. 장비 수리/정비 관리");
        System.out.println("4. 자산 이력 관리");
        System.out.print("번호 입력 : ");
    }
    
}
